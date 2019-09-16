package springtestkotlin.demo.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springtestkotlin.demo.dao.TestuserRepository
import springtestkotlin.demo.model.testuser

@RestController
@RequestMapping("/api/test")
class KotlinApiController {

    @RequestMapping("/{id}", method = arrayOf(RequestMethod.GET))
    open fun get(@PathVariable id: Long): String {
        return " $id's user test"
    }

    @Autowired
    lateinit var repository: TestuserRepository

    @RequestMapping("/save",method = arrayOf(RequestMethod.GET))
    fun save(): String {
        repository.save(testuser("AmyK"))
        repository.save(testuser("KAmy"))
        repository.save(testuser("KennethA"))
        repository.save(testuser("AKenneth"))

        return "Done"
    }

    @RequestMapping("/findall",method = arrayOf(RequestMethod.GET))
    fun findAll() = repository.findAll()

    @RequestMapping("/findbyid/{id}",method = arrayOf(RequestMethod.GET))
    fun findById(@PathVariable id: Long)
            = repository.findById(id)

    @RequestMapping("/findbyUsername/{userName}",method = arrayOf(RequestMethod.GET))
    fun findByUserName(@PathVariable userName: String)
            = repository.findByUserName(userName)
}