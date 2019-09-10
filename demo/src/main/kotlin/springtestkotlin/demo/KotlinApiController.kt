package springtestkotlin.demo

import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/test")
open class KotlinApiController{

    @RequestMapping("/{id}",  method = arrayOf(RequestMethod.GET))
    open fun get(@PathVariable id: Long):String{
        return " $id's user test"
    }
}