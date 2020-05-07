import groovy.lang.Binding
import java.sql.*
import groovy.sql.Sql
import groovy.lang.Script
import org.codehaus.groovy.control.CompilerConfiguration

/* Declaring a Map variable
to store the values and use it to update the template file with the right data
*/
Map variables = [:]

println "=================================="
println "Open DB connection"

def sql = Sql.newInstance('jdbc:mysql://10.10.4.70:3306/tradoria?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC','qa_ro_read','7QXbSnV6IarndFg9','com.mysql.cj.jdbc.Driver')

println "MySql version"
sql.eachRow('SELECT VERSION()'){ row ->
    println "Database version: ${row[0]}"
}

println "=================================="
println "Running thequeries and Storing the up to date Data in the variables"

sql.eachRow("select id as VARIANT_ID ,product_id as PRODUCT_ID from tradoria.product_variants where product_id in (select id from tradoria.products where stock>0 and stock_policy='track' and available='1' and portal='1')") { row ->
    variables.productId = row.PRODUCT_ID //all the values are stored in a variable inside the MAP
}
sql.eachRow("select id as VARIANT_ID ,product_id as PRODUCT_ID from tradoria.product_variants where product_id in (select id from tradoria.products where stock>0 and stock_policy='track' and available='1' and portal='1')") { row ->
    variables.variantId = row.VARIANT_ID //all the values are stored in a variable inside the MAP
}

// Creating a Binding to attach the variables to the execution scrip
Binding binding = new Binding()
binding.setVariable("variables", variables)

// Reading the template JSON file that should contain the JSON environment configuration
String jsonTemplate =
        new File('category-api-production.postman_environment.template').getText('UTF-8')

// Creating the script to execute the groovy code, this is the entry point
def configuration = new CompilerConfiguration()
configuration.setScriptBaseClass("groovy.lang.Script")

//linking the variable binded before to the script configuration
def shell = new GroovyShell(binding, configuration)

//executing the template
jsonTestData = shell.evaluate(jsonTemplate)

println "=================================="
println "writing and updating data in the environment file"

// creating the new env file with all data up to date
def configFile = new File("category-api-production.postman_environment.json")
configFile.withWriter('UTF-8') { writer ->
    writer.write jsonTestData
}

println "=================================="
println "Closing DB connection"
println "==============END================"

sql.close()
