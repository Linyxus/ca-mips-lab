package mips


import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class MainDecoderSpec extends FreeSpec with ChiselScalatestTester {

  "Main decoder should work properly" in {
    test(new MainDecoder()) { c =>
      case class OutputState(regWrite: Bool, regDst: Bool, aluSrc: Bool, branch: Bool, memWrite: Bool, memToReg: Bool, aluOp: UInt)
      def testState(s: OutputState): Unit = {
        c.io.regWrite.expect(s.regWrite)
        c.io.regDst.expect(s.regDst)
        c.io.aluSrc.expect(s.aluSrc)
        c.io.branch.expect(s.branch)
        c.io.memWrite.expect(s.memWrite)
        c.io.memToReg.expect(s.memToReg)
        c.io.aluOp.expect(s.aluOp)
      }

      val cases = Map(
        "b000000".U -> OutputState(true.B, true.B, false.B, false.B, false.B, false.B, "b10".U),
        "b100011".U -> OutputState(true.B, false.B, true.B, false.B, false.B, false.B, "b00".U),
        "b101011".U -> OutputState(false.B, false.B, true.B, false.B, false.B, true.B, "b00".U),
        "b000100".U -> OutputState(false.B, false.B, false.B, true.B, false.B, true.B, "b01".U),
      )

      cases foreach { case (i, o) =>
        println(f"input opcode: $i")
        c.io.opcode.poke(i)
        testState(o)
      }
    }
  }
}
