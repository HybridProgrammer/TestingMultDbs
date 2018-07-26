package testingmultdbs

import grails.gorm.transactions.Transactional
import grails.testing.mixin.integration.Integration
import spock.lang.Specification

@Integration
@Transactional
class MultipleDbsITSpec extends Specification {


    void "canary test to validate spock testing works"() {
        expect: "fix me"
        true == true
    }

    void "Test default db"() {
        given:
        new DefaultDb(f1: "test").save()

        when:
        def value = DefaultDb.findByF1("test")

        then:
        value
    }

    @Transactional("db2")
    void "Test default db2"() {
        given:
        new Db2Example(f1: "test").save()

        when:
        def value = Db2Example.findByF1("test")

        then:
        value
    }

    @Transactional("db3")
    void "Test default db3"() {
        given:
        new Db3Example(f1: "test").save()

        when:
        def value = Db3Example.findByF1("test")

        then:
        value
    }

    // Can only declare 1 @Transactional
//    @Transactional("db2") -- causes db3 to fail
//    @Transactional("db3") -- causes db2 to fail
//    @Transactional(["db2","db3"]) -- doesn't exist
    @Transactional
    void "Test interaction with all 3 databases"() {
        given:
        new DefaultDb(f1: "test").save()
        new Db2Example(f1: "test").save()
        new Db3Example(f1: "test").save()

        when:
        def value1 = DefaultDb.findByF1("test")
        def value2 = Db2Example.findByF1("test")
        def value3 = Db3Example.findByF1("test")

        then:
        value1
        value2
        value3
    }

    @Transactional
    void "Test interaction with all 3 databases Workaround"() {
        given:
        new DefaultDb(f1: "test").save()
        Db2Example.withNewTransaction {
            new Db2Example(f1: "test").save()
        }
        Db3Example.withNewTransaction {
            new Db3Example(f1: "test").save()
        }

        when:
        def value1 = DefaultDb.findByF1("test")
        def value2
        def value3
        Db2Example.withNewTransaction {
            value2 = Db2Example.findByF1("test")
        }
        Db3Example.withNewTransaction {
            value3 = Db3Example.findByF1("test")
        }

        then:
        value1
        value2
        value3
    }
}
