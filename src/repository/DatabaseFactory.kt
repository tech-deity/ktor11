package com.techdeity.repository

import com.techdeity.model.StudentTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {


    fun init(){
        Database.connect(hikari())
        transaction {
            SchemaUtils.create(StudentTable)
        }
    }



    fun hikari():HikariDataSource{
        val config=HikariConfig()
        config.driverClassName = System.getenv("JDBC_DRIVER")
        config.jdbcUrl = System.getenv("JDBC_DATABASE_URL")
        config.maximumPoolSize = 3
        config.isAutoCommit = false
        config.transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        config.validate()



        return HikariDataSource(config)
    }


    suspend fun <T> dbQuery(block : ()->T):T=
        withContext(Dispatchers.IO){
            transaction {
                block()
            }
        }
}