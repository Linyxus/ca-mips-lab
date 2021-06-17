package simple_cpu

import chisel3._
import util._
import chisel3.experimental._

class DecodeToExecute extends BlackBox {
  val io = IO {
    new Bundle {
      val clk = Input(Clock())
      val RegWriteD = Input(Bool())
      val MemtoRegD = Input(Bool())
      val MemWriteD = Input(Bool())
      val ALUSrcD = Input(Bool())
      val RegDstD = Input(Bool())
      val BranchD = Input(Bool())
      val ALUControlD = Input(UInt(3.W))
      val clk_count = Input(UInt(3.W))
      val RdD = Input(UInt(4.W))
      val RtD = Input(UInt(4.W))
      val RD1D = Input(UInt(32.W))
      val RD2D = Input(UInt(32.W))
      val SignImm32D = Input(UInt(32.W))
      val PCPlus4D = Input(UInt(32.W))
      val RegWriteE = Output(Bool())
      val MemtoRegE = Output(Bool())
      val MemWriteE = Output(Bool())
      val ALUSrcE = Output(Bool())
      val RegDstE = Output(Bool())
      val BranchE = Output(Bool())
      val ALUControlE = Output(UInt(3.W))
      val RdE = Output(UInt(5.W))
      val RtE = Output(UInt(5.W))
      val RD1E = Output(UInt(32.W))
      val RD2E = Output(UInt(32.W))
      val SignImm32E = Output(UInt(32.W))
      val PCPlus4E = Output(UInt(32.W))
    }
  }
}

