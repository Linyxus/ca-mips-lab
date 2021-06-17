package simple_cpu 

import chisel3._
import util._
import chisel3.experimental._

class PCF extends BlackBox {
  val io = IO {
    new Bundle {
      val clk = Input(Clock())
      val reset = Input(Bool())
      val clk_count = Input(UInt(3.W))
      val PC = Input(UInt(32.W))
      val PCF = Output(UInt(32.W))
      val PCPluse4F = Output(UInt(32.W))
    }
  }
}

