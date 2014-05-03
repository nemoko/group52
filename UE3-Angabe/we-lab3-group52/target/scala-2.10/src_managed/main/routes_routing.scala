// @SOURCE:/home/moko/Dropbox/SS14/webEng/group52/UE3-Angabe/we-lab3-group52/conf/routes
// @HASH:73b6ec746cf8e3013fb694b1577e466013e47b6b
// @DATE:Sat May 03 21:13:45 CEST 2014


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" }


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:9
private[this] lazy val controllers_Assets_at1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        

// @LINE:11
private[this] lazy val controllers_Application_sayHello2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("anonymoushello"))))
        

// @LINE:12
private[this] lazy val controllers_Application_sayHelloTo3 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("hello"))))
        

// @LINE:13
private[this] lazy val controllers_Application_sayHelloTo4 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("hello/"),DynamicPart("name", """[^/]+""",true))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """anonymoushello""","""controllers.Application.sayHello()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """hello""","""controllers.Application.sayHelloTo(name:String = "Stranger")"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """hello/$name<[^/]+>""","""controllers.Application.sayHelloTo(name:String)""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
      

def routes:PartialFunction[RequestHeader,Handler] = {

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:9
case controllers_Assets_at1(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        

// @LINE:11
case controllers_Application_sayHello2(params) => {
   call { 
        invokeHandler(controllers.Application.sayHello(), HandlerDef(this, "controllers.Application", "sayHello", Nil,"GET", """""", Routes.prefix + """anonymoushello"""))
   }
}
        

// @LINE:12
case controllers_Application_sayHelloTo3(params) => {
   call(Param[String]("name", Right("Stranger"))) { (name) =>
        invokeHandler(controllers.Application.sayHelloTo(name), HandlerDef(this, "controllers.Application", "sayHelloTo", Seq(classOf[String]),"GET", """""", Routes.prefix + """hello"""))
   }
}
        

// @LINE:13
case controllers_Application_sayHelloTo4(params) => {
   call(params.fromPath[String]("name", None)) { (name) =>
        invokeHandler(controllers.Application.sayHelloTo(name), HandlerDef(this, "controllers.Application", "sayHelloTo", Seq(classOf[String]),"GET", """""", Routes.prefix + """hello/$name<[^/]+>"""))
   }
}
        
}

}
     