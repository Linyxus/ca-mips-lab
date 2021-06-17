package simple_cpu

import chisel3._
import util._
import chisel3.experimental._

class FetchToDecode extends BlackBox {
  val io = IO {
    new Bundle {
      val clk = Input(Clock())
      val clk_count = Input(UInt(3.W))
      val InstrF = Input(UInt(32.W))
      val PCPlus4F = Input(UInt(32.W))
      val InstrD = Output(UInt(32.W))
      val PCPlus4D = Output(UInt(32.W))
    }
  }
}

