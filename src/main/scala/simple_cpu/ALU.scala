package simple_cpu

import chisel3._
import util._
import chisel3.experimental._

class ALU extends BlackBox {
  val io = IO {
    new Bundle {
      val SrcAE = Input(UInt(32.W))
      val DstAE = Input(UInt(32.W))
      val ALUControlE = Input(UInt(3.W))
      val ALUResultE = Output(UInt(32.W))
      val ZeroE = Output(Bool())
    }
  }
}

