package testingmultdbs

class BootStrap {

    def init = { servletContext ->
        new DefaultDb(f1: "test").save()
        new Db2Example(f1: "test").save()
        new Db3Example(f1: "test").save()


        def value1 = DefaultDb.findByF1("test")
        def value2 = Db2Example.findByF1("test")
        def value3 = Db3Example.findByF1("test")


        assert value1
        assert value2
        assert value3
    }
    def destroy = {
    }
}
