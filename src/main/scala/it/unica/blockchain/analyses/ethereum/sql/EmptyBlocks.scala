package it.unica.blockchain.analyses.ethereum.sql

import java.util.Date

import scalikejdbc._
import it.unica.blockchain.blockchains.BlockchainLib
import it.unica.blockchain.blockchains.ethereum.EthereumSettings
import it.unica.blockchain.db.sql.Table
import it.unica.blockchain.db.{DatabaseSettings, MySQL}

/**This analysis uses external data.
  * Make sure you have installed all the required libraries!
  * Checkout the README file */

object EmptyBlocks {
  def main(args: Array[String]): Unit = {
    /** Signup to Infura and insert your Project ID into the URL, after "https://mainnet.infura.io/" **/
    val blockchain = BlockchainLib.getEthereumBlockchain(new EthereumSettings("https://mainnet.infura.io/InsertYourProjectID:8545"))
    val pg = new DatabaseSettings("ethereum", MySQL, "root", "toor")

    val blockTable = new Table(
      sql"""
          CREATE TABLE IF NOT EXISTS block(
            hash VARCHAR(100) NOT NULL PRIMARY KEY,
            timestamp TIMESTAMP,
            miner VARCHAR(100)
          )
         """,
      sql"""
          INSERT INTO block(hash,timestamp, miner) VALUES (?, ?, ?)
         """,
      pg, 1
    )


    blockchain.foreach(block => {
      if (block.height % 100 == 0) {
        println(block.height)
      }

      if (block.txs.isEmpty) {
        blockTable.insert(Seq(block.hash, block.date, block.miner))
      }
    })

    blockTable.close
  }
}
