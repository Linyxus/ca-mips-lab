package simple_cpu

import chisel3._
import util._
import chisel3.experimental._

class PCB extends BlackBox {
  val io = IO {
    new Bundle {
      val PCPluse4E = Input(UInt(32.W))
      val SignImm32E = Input(UInt(32.W))
      val PCBranchE = Output(UInt(32.W))
    }
  }
}

