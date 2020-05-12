package it.unica.blockchain.blockchains.ethereum

import it.unica.blockchain.blockchains.Transaction

/**
  * Defines an Ethereum Internal Transaction
  *
  * @param parentTxHash hash of the transaction parent
  * @param txType transaction type (create, suicide, call)
  * @param from who create transaction
  * @param to who "receives" transaction
  * @param value value of transaction
  */
case class EthereumInternalTransaction(
                                parentTxHash: String,
                                txType: String,
                                from: EthereumAddress,
                                to: EthereumAddress,
                                value: BigInt
                              ) {
  override def toString(): String = {
    "parentTxHash: " + parentTxHash +
    "txType: " + txType +
    "from: " + from +
    "to: " + to +
    "value: " + value
  }
}
