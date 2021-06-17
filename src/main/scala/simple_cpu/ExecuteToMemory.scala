package simple_cpu

import chisel3._
import util._
import chisel3.experimental._

class ExecuteToMemory extends BlackBox {
  val io = IO {
    new Bundle {
      val clk = Input(Clock())
      val RegWriteE = Input(Bool())
      val MemtoRegE = Input(Bool())
      val MemWriteE = Input(Bool())
      val BranchE = Input(Bool())
      val ZeroE = Input(Bool())
      val clk_count = Input(UInt(3.W))
      val WriteRegE = Input(UInt(5.W))
      val ALUResultE = Input(UInt(32.W))
      val WriteDataE = Input(UInt(32.W))
      val PCBranchE = Input(UInt(32.W))
      val RegWriteM = Output(Bool())
      val MemtoRegM = Output(Bool())
      val MemWriteM = Output(Bool())
      val BranchM = Output(Bool())
      val ZeroM = Output(Bool())
      val WriteRegM = Output(UInt(5.W))
      val ALUOutM = Output(UInt(32.W))
      val WriteDataM = Output(UInt(32.W))
      val PCBranchM = Output(UInt(32.W))
    }
  }
}

