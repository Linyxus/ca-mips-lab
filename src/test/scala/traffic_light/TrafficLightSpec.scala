package traffic_light

import chisel3._
import chisel3.tester._
import chiseltest.internal.WriteVcdAnnotation
import chiseltest.experimental.TestOptionBuilder._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class TrafficLightSpec extends FreeSpec with ChiselScalatestTester {

  "Traffic signals should work properly" in {
    test(new TrafficLight()).withAnnotations(Seq(WriteVcdAnnotation)) { c =>
      def test1 = {
        c.io.mainRed.expect(false.B)
        c.io.mainGreen.expect(true.B)
        c.io.mainYellow.expect(false.B)
        c.io.branchRed.expect(true.B)
        c.io.branchGreen.expect(false.B)
        c.io.branchYellow.expect(false.B)
      }
      def test2 = {
        c.io.mainRed.expect(false.B)
        c.io.mainGreen.expect(false.B)
        c.io.mainYellow.expect(true.B)
        c.io.branchRed.expect(true.B)
        c.io.branchGreen.expect(false.B)
        c.io.branchYellow.expect(false.B)
      }
      def test3 = {
        c.io.mainRed.expect(true.B)
        c.io.mainGreen.expect(false.B)
        c.io.mainYellow.expect(false.B)
        c.io.branchRed.expect(false.B)
        c.io.branchGreen.expect(true.B)
        c.io.branchYellow.expect(false.B)
      }
      def test4 = {
        c.io.mainRed.expect(true.B)
        c.io.mainGreen.expect(false.B)
        c.io.mainYellow.expect(false.B)
        c.io.branchRed.expect(false.B)
        c.io.branchGreen.expect(false.B)
        c.io.branchYellow.expect(true.B)
      }

      def testRound = {
        test1
        c.clock.step(14)
        test1
        c.clock.step(1)
        test2
        c.clock.step(2)
        test2
        c.clock.step(1)
        test3
        c.clock.step(6)
        test3
        c.clock.step(1)
        test4
        c.clock.step(2)
        test4
        c.clock.step(1)
        test1
      }

      for (_ <- 1 to 3) { testRound }
    }
  }

}

