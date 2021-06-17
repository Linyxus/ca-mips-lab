package simple_cpu

import chisel3._
import util._
import chisel3.experimental._
import chisel3.stage.{ChiselGeneratorAnnotation, ChiselStage}


class CpuTop extends Module {
  val io = IO {
    new Bundle {
    }
 }

  val clkCount = Module { new clk_count() }

  val pc = Module { new PC() }

  val pcf = Module { new PCF() }

  val rom = Module { new ROM() }

  val register = Module { new Register() }

  val signExtend = Module { new SignExtend() }

  val alu = Module { new ALU() }

  val controlUnit = Module { new ControlUnit() }

  val f2d = Module { new FetchToDecode() }

  val d2e = Module { new DecodeToExecute() }

  val pcb = Module { new PCB() }

  val e2m = Module { new ExecuteToMemory() }

  val ram = Module { new RAM() }

  // clk count
  clkCount.io.clk := clock
  clkCount.io.reset := reset

  // pc
  pc.io.PCSrcM := e2m.io.BranchM & e2m.io.ZeroM
  pc.io.PCBranchM := e2m.io.PCBranchM
  pc.io.PCPlus4F := pcf.io.PCPluse4F

  // pcf
  pcf.io.clk := clock
  pcf.io.reset := reset
  pcf.io.clk_count := clkCount.io.count
  pcf.io.PC := pc.io.PC

  // rom
  rom.io.A := pcf.io.PCF

  // fetch to decode
  f2d.io.clk := clock
  f2d.io.clk_count := clkCount.io.count
  f2d.io.InstrF := rom.io.RD
  f2d.io.PCPlus4F := pcf.io.PCPluse4F

  // control unit
  val instr = f2d.io.InstrD
  controlUnit.io.Op := instr(31, 26)
  controlUnit.io.Funct := instr(5, 0)

  // register file
  register.io.clk := clock
  register.io.WE3 := e2m.io.RegWriteM
  register.io.A1 := instr(25, 21)
  register.io.A2 := instr(20, 16)
  register.io.A3 := e2m.io.WriteRegM
  register.io.WD3 := Mux(e2m.io.MemtoRegM, ram.io.RD, e2m.io.ALUOutM)

  // sign extend
  signExtend.io.SignImm16 := instr(15, 0)

  // alu
  alu.io.SrcAE := d2e.io.RD1E
  alu.io.DstAE := Mux(d2e.io.ALUSrcE, d2e.io.SignImm32E, d2e.io.RD2E)
  alu.io.ALUControlE := d2e.io.ALUControlE

  // decode to execute
  d2e.io.clk := clock
  d2e.io.RegWriteD := controlUnit.io.RegWriteD
  d2e.io.MemtoRegD := controlUnit.io.MemtoRegD
  d2e.io.MemWriteD := controlUnit.io.MemWriteD
  d2e.io.BranchD := controlUnit.io.BranchD
  d2e.io.ALUControlD := controlUnit.io.ALUControlD
  d2e.io.ALUSrcD := controlUnit.io.ALUSrcD
  d2e.io.RegDstD := controlUnit.io.RegDstD
  d2e.io.clk_count := clkCount.io.count
  d2e.io.RdD := instr(20, 16)
  d2e.io.RtD := instr(15, 11)
  d2e.io.RD1D := register.io.RD1
  d2e.io.RD2D := register.io.RD2
  d2e.io.SignImm32D := signExtend.io.SignImm32
  d2e.io.PCPlus4D := f2d.io.PCPlus4D

  // execute to memory
  e2m.io.clk := clock
  e2m.io.RegWriteE := d2e.io.RegWriteE
  e2m.io.MemtoRegE := d2e.io.MemtoRegE
  e2m.io.MemWriteE := d2e.io.MemWriteE
  e2m.io.BranchE := d2e.io.BranchE
  e2m.io.ZeroE := alu.io.ZeroE
  e2m.io.clk_count := clkCount.io.count
  e2m.io.WriteRegE := Mux(d2e.io.RegDstE, d2e.io.RtE, d2e.io.RdE)
  e2m.io.ALUResultE := alu.io.ALUResultE
  e2m.io.WriteDataE := d2e.io.RD2E
  e2m.io.PCBranchE := pcb.io.PCBranchE

  // pc branch
  pcb.io.PCPluse4E := d2e.io.PCPlus4E
  pcb.io.SignImm32E := d2e.io.SignImm32E

  // ram
  ram.io.clk := clock
  ram.io.WE := e2m.io.MemWriteM
  ram.io.A := e2m.io.ALUOutM
  ram.io.WD := e2m.io.WriteDataM
}

object CpuTop extends App {
  new ChiselStage execute(args, Seq(ChiselGeneratorAnnotation(() => new CpuTop())))
}
