package springboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.annotation.Id
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.relational.core.mapping.Table
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.w3c.dom.Document
import org.xml.sax.InputSource
import java.io.File
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}



@RestController
class PatientResource(val service: PatientService) {
    @GetMapping
    fun index(): List<Patient> = service.findPatients()

    @PostMapping
    fun post(@RequestBody patient: Patient) {
        service.post(patient)
    }
}

@Service
class PatientService(val db: PatientRepository) {

    fun findPatients(): List<Patient> = db.findPatients()

    fun post(patient: Patient) {
        db.save(patient)
    }
}

interface PatientRepository: CrudRepository<Patient, String> {
    @Query("select * from patients")
    fun findPatients(): List<Patient>
}

@Service
class ResultService(val db: ResultRepository) {
    fun findResults(): List<Result> = db.findResults()


}

interface ResultRepository: CrudRepository<Result, String> {
    @Query("select * from results")
    fun findResults(): List<Result>

}

@Table("PATIENTS")
data class Patient(@Id  val id: String?, val firstname: String, val lastname: String, val diseases: String?)

@Table("RESULTS")
data class Result(@Id val doctor_id: String?, val execution_time: Int, val error: String?, val doc_source: String )