package com.example.cookie.models


import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class ConnexionInfo(@Id @GeneratedValue
                var email: String? = null,
                var password: String? = null) {}