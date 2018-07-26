package testingmultdbs

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class DefaultDbSpec extends Specification implements DomainUnitTest<DefaultDb> {

    def setup() {
    }

    def cleanup() {
    }

    void "test something"() {
        expect:"fix me"
            true == true
    }
}
