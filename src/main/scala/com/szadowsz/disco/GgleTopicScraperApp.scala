package com.szadowsz.disco

import com.szadowsz.maeve.core.browser.MaeveConf
import com.szadowsz.maeve.gglegrp.GgleTopicScraper
import org.slf4j.LoggerFactory

/**
  * Created on 19/10/2016.
  */
object GgleTopicScraperApp {
  private val logger = LoggerFactory.getLogger(this.getClass)
  //Reviewed
  // private val link = "https://groups.google.com/forum/#!forum/alt.sci.planetary"
  //private val link = "https://groups.google.com/forum/#!forum/comp.sources.d"

  // TODO Reviewing
  //  private val link = "https://groups.google.com/forum/#!forum/comp.lang.pascal"

  // TODO Review
  //  private val link = "https://groups.google.com/forum/#!forum/rec.games.frp"
   // private val link = "https://groups.google.com/forum/#!forum/rec.arts.sf.science"
 // private val link = "https://groups.google.com/forum/#!forum/rec.games.frp.misc"
  //  private val link = "https://groups.google.com/forum/#!forum/comp.sources.wanted"
  //private val link = "https://groups.google.com/forum/#!forum/comp.sources.misc"
  // private val link = "https://groups.google.com/forum/#!forum/comp.sys.apple2"
//  private val link = "https://groups.google.com/forum/#!forum/rec.games.frp.cyber"
//  private val link = "https://groups.google.com/forum/#!forum/rec.games.programmer"
  //   private val link = "https://groups.google.com/forum/#!forum/rec.games.frp.misc"
 // private val link = "https://groups.google.com/forum/#!forum/rec.games.design"


  // TODO Retrieve
 //private val link = "https://groups.google.com/forum/#!forum/sci.astro"
  private val link = "https://groups.google.com/forum/#!forum/rec.games.frp.dnd"

  private val dir = "./data/grp/"
  private val scraper = new GgleTopicScraper(link, dir, None, false)

  def main(args: Array[String]): Unit = {
    System.setProperty("webdriver.chrome.driver", ".\\chromedriver_win32\\chromedriver.exe")
    scraper.execute()
  }
}
