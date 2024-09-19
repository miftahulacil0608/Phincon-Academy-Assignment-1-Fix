package com.team1.assignment1_ahmadmiftahulazisz.assignment1

import com.team1.assignment1_ahmadmiftahulazisz.assignment1.Helper.dateConvertToString
import com.team1.assignment1_ahmadmiftahulazisz.assignment1.Helper.regexFormat
import java.time.LocalDate
import kotlin.system.exitProcess

class PersonalFinance {
    private var numberOfBalance = 3_000_000L
    private var totalOfIncome = 3_500_000L
    private var totalOfExpense = 500_000L
    private var listOfTransaction: MutableList<DataTransaction> = mutableListOf(
        DataTransaction(
            typeTransaction = "Pemasukan",
            numberOfTransaction = 3_500_000L,
            description = "Keuntungan Jual beli HP",
            date = "01-09-2024"
        ),
        DataTransaction(
            typeTransaction = "Pengeluaran",
            numberOfTransaction = 500_000L,
            description = "Kebutuhan bulanan",
            date = "15-09-2024"
        )
    )

    fun addTransaction(typeOfTransaction: String) {
        var condition: Boolean
        do {
            println("====== Tambahkan $typeOfTransaction ======")
            print("Nominal $typeOfTransaction: ")
            val inputNominal = readlnOrNull()
            print("Keterangan: ")
            val inputDescription = readlnOrNull()

            // atau "[0-9]+" pattern regex untuk mengecek inputan awal-akhir berupa angka atau bukan jika menggunakan .matches
            val validateInputNominal = inputNominal?.let { regexFormat.matches(it) } ?: false

            // inputan dicek dulu 0 atau endak, jika iya kembalikan 0, jika tidak gunakan lengthnya dan lihat apakah >= dengan 5
            val validateDescription = (inputDescription?.length ?: 0) >= 5

            when {
                !validateInputNominal -> {
                    println("Inputan nominal anda harus angka")
                    condition = true
                }

                !validateDescription -> {
                    println("Deskripsi yang anda berikan minimal 5 huruf")
                    condition = true
                }

                else -> {
                    val convertNominalToLong = inputNominal?.toLong() ?: 0L
                    calculateMoney(
                        numberOfMoney = convertNominalToLong,
                        inputDescription = inputDescription,
                        typeOfTransaction = typeOfTransaction
                    )
                    condition = false
                }
            }
        } while (condition)

    }

    private fun calculateMoney(
        numberOfMoney: Long,
        inputDescription: String?,
        typeOfTransaction: String
    ) {
        if (typeOfTransaction == "Pemasukan") {
            numberOfBalance += numberOfMoney //total uang
            listOfTransaction.add(
                DataTransaction(
                    typeTransaction = typeOfTransaction,
                    numberOfTransaction = numberOfMoney,
                    description = inputDescription,
                    date = LocalDate.now().dateConvertToString()
                )
            )
            totalOfIncome += numberOfMoney //total pendapatan

        } else if (typeOfTransaction == "Pengeluaran") {
            numberOfBalance -= numberOfMoney //total uang
            listOfTransaction.add(
                DataTransaction(
                    typeOfTransaction,
                    numberOfTransaction = numberOfMoney,
                    description = inputDescription,
                    date = LocalDate.now().dateConvertToString()
                )
            )
            totalOfExpense += numberOfMoney //total pengeluaran
        }
    }

    fun riwayatTransaksi() {
        println("====== Riwayat Transaksi ======")
        listOfTransaction.forEach {
            println(
                """
                        ---Tanggal ${it.date}---
                Jenis Transaksi     : ${it.typeTransaction}
                Jumlah Transaksi    : ${it.numberOfTransaction}
                Deskripsi Transaksi : ${it.description}
            """.trimIndent()
            )
        }
        inputMenuUser()
    }


    fun analysisOfMoney() {
        println("====== Keuangan Anda ======")
        println(
            """
            Total Pemasukan     : Rp. $totalOfIncome
            Total Pengeluaran   : Rp. $totalOfExpense
            Jumlah saldo akhir  : Rp. $numberOfBalance
            
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
            val inputMenuUser = readln()
            when (inputMenuUser) {
                "98" -> {
                    return
                }

                "99" -> {
                    //status 0 itu normal exit
                    exitProcess(0)
                }

                else -> {
                    println("Inputan anda salah")
                }
            }
        } while (true)
    }

}