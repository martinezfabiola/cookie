package com.example.cookie.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class User(@Id @GeneratedValue var id: Int? =  null,
                var firstname: String? = null,
                var lastname: String?= null,
                var email: String? = null,
                var phone: String? = null,
                var address: String? = null,
                var citycode: String? = null,
                var password: String? = null,
                var historique: String? = null,
                var shopbag: String? = null) {

}