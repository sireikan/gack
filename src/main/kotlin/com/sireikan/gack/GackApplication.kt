package com.sireikan.gack

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GackApplication

fun main(args: Array<String>) {
    runApplication<GackApplication>(*args)
}
