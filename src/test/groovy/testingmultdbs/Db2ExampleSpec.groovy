package testingmultdbs

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class Db2ExampleSpec extends Specification implements DomainUnitTest<Db2Example> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == true
    }
}
