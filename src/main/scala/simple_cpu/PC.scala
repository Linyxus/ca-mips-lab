package simple_cpu

import chisel3._
import util._
import chisel3.experimental._

class PC extends BlackBox {
  val io = IO {
    new Bundle {
      val PCSrcM = Input(Bool())
      val PCBranchM = Input(UInt(32.W))
      val PCPlus4F = Input(UInt(32.W))
      val PC = Output(UInt(32.W))
    }
  }
}

