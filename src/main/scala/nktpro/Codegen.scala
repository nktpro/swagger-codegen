package nktpro

import io.swagger.codegen.SwaggerCodegen

/**
  * Created by NKTPRO on 10/17/15.
  */
object Codegen {
  def main(args: Array[String]) {
    SwaggerCodegen.main("generate -i temp/swagger.json -l nktpro.SwaggerTypeScriptClientCodegen -o temp".split(" "))
  }
}
