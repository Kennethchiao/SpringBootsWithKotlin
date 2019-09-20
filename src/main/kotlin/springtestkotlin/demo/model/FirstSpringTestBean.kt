package springtestkotlin.demo.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class testuser(
        val userName: String,
        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        val id: Long = -1) {
    private constructor() : this("")
}
