package com.team1.phinconacademyassignment1

import com.team1.phinconacademyassignment1.Helper.dateConvertToString
import com.team1.phinconacademyassignment1.Helper.regexFormat
import java.time.LocalDate
import kotlin.system.exitProcess

class PersonalFinance {
    private var numberOfBalance = 0L
    private var totalOfIncome = 0L
    private var totalOfExpense = 0L
    private var listOfTransaction: MutableList<DataTransaction> = mutableListOf()

    fun addTransaction(typeInput: TypeInput) {

        while (true) {
            println("====== Tambahkan $typeInput ======")
            print("Nominal $typeInput: ")
            val inputNominal = readln().trim()

            val validateInputNominal = regexFormat.matches(inputNominal)
            if (!validateInputNominal) {
                println("Inputan nominal salah")
                continue
            }

            print("Keterangan: ")
            val inputDescription = readln().trim()
            val validateDescription = inputDescription.length >= 5
            if (!validateDescription) {
                println("Deskripsi yang anda berikan minimal 5 huruf")
                continue
            }

            val convertNominalToLong = inputNominal.toLong()
            calculateMoney(
                numberOfMoney = convertNominalToLong,
                inputDescription = inputDescription,
                typeOfTransaction = typeInput
            )
            break
        }
    }

    private fun calculateMoney(
        numberOfMoney: Long,
        inputDescription: String,
        typeOfTransaction: TypeInput
    ) {
        if (typeOfTransaction == TypeInput.PEMASUKAN) {
            numberOfBalance += numberOfMoney //total uang
            listOfTransaction.add(
                DataTransaction(
                    typeTransaction = typeOfTransaction.toString(),
                    numberOfTransaction = numberOfMoney,
                    description = inputDescription,
                    date = LocalDate.now().dateConvertToString(),
                    lastBalance = numberOfBalance
                )
            )
            totalOfIncome += numberOfMoney //total pendapatan

        } else if (typeOfTransaction == TypeInput.PENGELUARAN) {
            numberOfBalance -= numberOfMoney //total uang
            listOfTransaction.add(
                DataTransaction(
                    typeOfTransaction.toString(),
                    numberOfTransaction = numberOfMoney,
                    description = inputDescription,
                    date = LocalDate.now().dateConvertToString(),
                    lastBalance = numberOfBalance
                )
            )
            totalOfExpense += numberOfMoney //total pengeluaran
        }
    }

    fun financialHistory() {
        if (listOfTransaction.isEmpty()){
            println("Tidak Ada Riwayat")
        }else{
            println("====== Riwayat Transaksi ======")
            listOfTransaction.forEach {
                println(
                    """
                        ---Tanggal ${it.date}---
                Jenis Transaksi     : Rp ${it.typeTransaction}
                Jumlah Transaksi    : Rp ${it.numberOfTransaction}
                Deskripsi Transaksi : Rp ${it.description}
                Total Uang          : Rp ${it.lastBalance}
            """.trimIndent()
                )
            }
        }

        inputMenuUser()
    }


    fun financialAnalysis() {
        println("====== Keuangan Anda ======")
        println(
            """
            Total Pemasukan     : Rp $totalOfIncome
            Total Pengeluaran   : Rp $totalOfExpense
            Jumlah saldo akhir  : Rp $numberOfBalance
            
        """.trimIndent()
        )
        inputMenuUser()
    }

    private fun inputMenuUser() {
        do {
            println(
                """
            Pilih Menu:
                98. Kembali
                99. Keluar
        """.trimIndent()
            )
            print("Your Choice: ")
            val inputMenuUser = readln().trim()
            when (inputMenuUser) {
                "98" -> return
                "99" -> exitProcess(0)
                else -> println("Inputan anda salah")
            }
        } while (true)
    }

}