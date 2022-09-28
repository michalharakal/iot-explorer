plugins {
    id("org.asciidoctor.jvm.convert") version "2.4.0"
}

/*
repositories {
    ruby.gems()
}

dependencies {
    asciidoctorGems ("rubygems:rouge:3.15.0")
}

 */


tasks.withType<org.asciidoctor.gradle.jvm.AsciidoctorTask> {
    //sourceDir.s = file("docs")
    /*
    sources(delegateClosureOf<PatternSet> {
        include("toplevel.adoc", "another.adoc", "third.adoc")
    })

    outputDir = file("build/docs")


     */
}

