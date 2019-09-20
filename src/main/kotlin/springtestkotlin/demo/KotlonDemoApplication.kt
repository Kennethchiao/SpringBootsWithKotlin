package springtestkotlin.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class KotlonDemoApplication

fun main(args: Array<String>) {
    runApplication<KotlonDemoApplication>(*args)
}
