import grails.util.BuildSettings
import grails.util.Environment

// See http://logback.qos.ch/manual/groovy.html for details on configuration
appender('STDOUT', ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%level %logger - %msg%n"
    }
}

root(ERROR, ['STDOUT'])

def targetDir = BuildSettings.TARGET_DIR
if (Environment.isDevelopmentMode() && targetDir) {
    appender("FULL_STACKTRACE", FileAppender) {
        file = "${targetDir}/stacktrace.log"
        append = true
        encoder(PatternLayoutEncoder) {
            pattern = "%level %logger - %msg%n"
        }
    }
    logger "StackTrace", ERROR, ['FULL_STACKTRACE'], false
    logger 'grails.app.services', TRACE, ['STDOUT'], false
    logger 'grails.app.controllers', TRACE, ['STDOUT'], false
    logger 'grails.app.jobs', TRACE, ['STDOUT'], false
} else {
    logger 'grails.app.controllers', INFO, ['STDOUT']
    logger 'grails.app.services', INFO, ['STDOUT']
    logger 'grails.app.jobs', INFO, ['STDOUT']
}
