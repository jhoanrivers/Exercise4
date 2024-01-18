package com.example.exercise4.data.local.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import java.util.UUID


@RealmClass
open class EmployeeEntity (
    var name: String,
    var email: String,
) : RealmObject() {

    @PrimaryKey
    var id: String = UUID.randomUUID().toString()

    constructor() : this("","")


}