package testingmultdbs

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class Db3ExampleSpec extends Specification implements DomainUnitTest<Db3Example> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == true
    }
}
