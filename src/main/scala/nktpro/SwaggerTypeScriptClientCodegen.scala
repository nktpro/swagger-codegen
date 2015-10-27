package nktpro

import io.swagger.codegen._
import io.swagger.codegen.languages.AbstractTypeScriptClientCodegen

//remove if not needed
class SwaggerTypeScriptClientCodegen extends AbstractTypeScriptClientCodegen() {

  override def getName(): String = "typescript-client"

  override def getHelp(): String = {
    "Generates a TypeScript client library."
  }

  outputFolder = "generated-code/typescript-client"

  templateDir = "typescript-client"

  supportingFiles.add(new SupportingFile("api.mustache", null, "api.ts"))
  supportingFiles.add(new SupportingFile("tsconfig.json", null, "tsconfig.json"))
  supportingFiles.add(new SupportingFile("tsd.d.ts", null, "tsd.d.ts"))
  supportingFiles.add(new SupportingFile("typings/bluebird/bluebird.d.ts", "typings/bluebird/", "bluebird.d.ts"))
  supportingFiles.add(new SupportingFile("typings/superagent/superagent.d.ts", "typings/superagent/", "superagent.d.ts"))
  supportingFiles.add(new SupportingFile("typings/node/node.d.ts", "typings/node/", "node.d.ts"))
}