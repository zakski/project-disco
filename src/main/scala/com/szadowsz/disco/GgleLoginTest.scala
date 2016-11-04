package com.szadowsz.tarbh.ggle

import com.szadowsz.common.net.Uri
import com.szadowsz.maeve.core.MaeveDriver
import com.szadowsz.maeve.core.browser.MaeveConf
import com.szadowsz.maeve.core.instruction.MaeveInstruction
import com.szadowsz.maeve.core.instruction.extractor.JsoupExtractor
import com.szadowsz.maeve.core.instruction.target.single.SingleTarget
import com.szadowsz.maeve.gglegrp.actions.GgleExecutor
import org.jsoup.nodes.Document

/**
  * Rendered Necessary Unfortunately.
  *
  * Created on 19/10/2016.
  */
object GgleLoginTest {
  private val link = ""
  private val username: String = ""
  private val passwd: String = ""
  private val urlOfGrp = Uri(link)
  private val groupName: String = ""

  private def buildConfig(): MaeveConf = {
    MaeveConf()
      .setJavaScriptEnabled(true)
      .setHTTPProxy("", 0, Nil)
      .setThrowExceptionOnScriptError(false)
  }



  def main(args: Array[String]): Unit = {
    System.setProperty("webdriver.chrome.driver", ".\\chromedriver_win32\\chromedriver.exe")
    val conf = buildConfig()
    val scraper = new MaeveDriver(conf)

    val rootTarget = SingleTarget(urlOfGrp)
    class TestExtractor extends JsoupExtractor {
      override def extract(queryUrl: Uri, returnedUrl: Uri, inst: MaeveInstruction[_], page: Document): Unit = {}
      override def shouldContinue(): Boolean = false
    }
    val rootFilter = new TestExtractor()

    val actions = new GgleExecutor(username, passwd)
    val rootInstruction = MaeveInstruction(groupName, rootTarget, actions, rootFilter, "./data/grp/", false, false,MaeveConf().setNoProxy())

    scraper.feedInstruction(rootInstruction)
    scraper.scrapeUsingCurrInstruction()
  }
}
