# DEBUG,INFO,WARN,ERROR,FATAL
LOG_LEVEL=DEBUG

log4j.rootLogger=${r'${LOG_LEVEL}'},CONSOLE,FILE

log4j.appender.CONSOLE=org.apache.log4j.ConsoleAppender
log4j.appender.CONSOLE.Encoding=utf-8
log4j.appender.CONSOLE.layout=org.apache.log4j.PatternLayout
#log4j.appender.CONSOLE.layout.ConversionPattern=[%-5p] %d{yyyy-MM-dd HH:mm:ss} %C{8}@(%F:%L):%m%n 
log4j.appender.CONSOLE.layout.ConversionPattern=[%-5p] %d{yyyy-MM-dd HH:mm:ss} %C{1}@(%F:%L):%m%n

log4j.appender.FILE=org.apache.log4j.DailyRollingFileAppender
log4j.appender.FILE.File=${r'${catalina.base}'}/logs/quick4j.log
log4j.appender.FILE.Encoding=utf-8
log4j.appender.FILE.DatePattern='.'yyyy-MM-dd
log4j.appender.FILE.layout=org.apache.log4j.PatternLayout
#log4j.appender.FILE.layout=org.apache.log4j.HTMLLayout
log4j.appender.FILE.layout.ConversionPattern=[%-5p] %d{yyyy-MM-dd HH\:mm\:ss} %C{8}@(%F\:%L)\:%m%n 

