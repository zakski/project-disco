package com.szadowsz.tarbh.ggle

import com.szadowsz.common.net.Uri
import com.szadowsz.maeve.core.MaeveDriver
import com.szadowsz.maeve.core.browser.MaeveConf
import com.szadowsz.maeve.core.instruction.MaeveInstruction
import com.szadowsz.maeve.core.instruction.target.multi.feeder.FragmentFeederTarget
import com.szadowsz.maeve.core.instruction.target.single.SingleTarget
import com.szadowsz.maeve.gglegrp.actions.{GgleExecutor, GgleGrpExecutor}
import com.szadowsz.maeve.gglegrp.extractor.GrpExtractor
import org.slf4j.LoggerFactory

/**
  * Rendered Necessary Unfortunately.
  *
  * Created on 19/10/2016.
  */
object GgleGrpScraper {
  private val logger = LoggerFactory.getLogger(this.getClass)
  private val link = "https://groups.google.com/forum/#!forum/rec.games.frp.dnd"
  private val username: String = ""
  private val passwd: String = ""
  private val urlOfGrp = Uri(link)
  private val groupName: String = link.substring(link.lastIndexOf("/") + 1)

  private def buildConfig(): MaeveConf = {
    MaeveConf()
      .setJavaScriptEnabled(true)
      .setThrowExceptionOnScriptError(false)
  }


  def main(args: Array[String]): Unit = {
    System.setProperty("webdriver.chrome.driver", ".\\chromedriver_win32\\chromedriver.exe")
    val conf = buildConfig().setNoProxy().setSkipProxyTestEnabled(true)
    val scraper = new MaeveDriver(conf)

    val target = FragmentFeederTarget(Uri("https://groups.google.com/forum/"))
    val rootTarget = SingleTarget(urlOfGrp)
    val rootFilter = new GrpExtractor()

   val yearfilters = (1986, 1989) +: Range.inclusive(1989, 2006).toList.map(st => (st, st + 1)) :+ (2006, 2017)
 //  val yearfilters = Range.inclusive(1986, 1988).toList.map(st => (st, st + 1)) //:+ (2014, 2017)
    yearfilters.foreach { case (fStart, fEnd) =>
      logger.info("Getting Topics Between {} : {}",fStart,fEnd)
      val actions = new GgleGrpExecutor(fStart, fEnd, username, passwd)
      val rootInstruction = MaeveInstruction(groupName, rootTarget, actions, rootFilter, "./data/grp/", false, false, false,MaeveConf().setNoProxy()
        .setSkipProxyTestEnabled(true))

      scraper.feedInstruction(rootInstruction)
      scraper.scrapeUsingCurrInstruction()
    }
  }
}
