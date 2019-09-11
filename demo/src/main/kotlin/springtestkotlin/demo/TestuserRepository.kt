package springtestkotlin.demo

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository

interface TestuserRepository : CrudRepository<testuser, Long> {

    fun findByUserName(UserName: String): Iterable<testuser>
}