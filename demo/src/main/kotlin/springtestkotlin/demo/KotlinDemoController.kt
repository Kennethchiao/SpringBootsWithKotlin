package springtestkotlin.demo

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class DemoController{
    @GetMapping("/")
    fun home(): String {return "Hi this is index page"}

    @GetMapping("/test")
    fun test(): String {return "this is test page"}
}