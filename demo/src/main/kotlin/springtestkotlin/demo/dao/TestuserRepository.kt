package springtestkotlin.demo.dao

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import springtestkotlin.demo.model.testuser

@Repository

interface TestuserRepository : CrudRepository<testuser, Long> {

    fun findByUserName(UserName: String): Iterable<testuser>
}