package simple_cpu

import chisel3._
import util._
import chisel3.experimental._

class ControlUnit extends BlackBox {
  val io = IO {
    new Bundle {
      val Op = Input(UInt(6.W))
      val Funct = Input(UInt(6.W))
      val MemtoRegD = Output(Bool())
      val MemWriteD = Output(Bool())
      val BranchD = Output(Bool())
      val ALUSrcD = Output(Bool())
      val RegDstD = Output(Bool())
      val RegWriteD = Output(Bool())
      val ALUControlD = Output(UInt(3.W))
    }
  }
}

