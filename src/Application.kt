package com.techdeity

import com.techdeity.repository.DatabaseFactory
import com.techdeity.repository.StudentRepository
import com.techdeity.routes.student
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.gson.*
import io.ktor.features.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    DatabaseFactory.init()
    val db = StudentRepository()
    install(Locations) {
    }

    install(ContentNegotiation) {
        gson {

            setPrettyPrinting()
        }
    }

    routing {

        student(db)
    }
}

const val API_VERSION = "v1/"



