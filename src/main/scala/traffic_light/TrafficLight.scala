package traffic_light

import chisel3._
import util._
import chisel3.experimental.ChiselEnum

class TrafficLight extends Module {
  object State extends ChiselEnum {
    val S1, S2, S3, S4 = Value
  }

  val io = IO {
    new Bundle {
      // Traffic light for main rpad
      val mainRed = Output(Bool())
      val mainGreen = Output(Bool())
      val mainYellow = Output(Bool())
      // Traffic light for branch road
      val branchRed = Output(Bool())
      val branchGreen = Output(Bool())
      val branchYellow = Output(Bool())
    }
  }

  val state = RegInit(State.S1)
  val counter = RegInit(0.U(4.W))

  switch (state) {
    is (State.S1) {
      when (counter < 14.U) {
        counter := counter + 1.U
        state := State.S1
      } .otherwise {
        counter := 0.U
        state := State.S2
      }
    }

    is (State.S2) {
      when (counter < 2.U) {
        counter := counter + 1.U
        state := State.S2
      } .otherwise {
        counter := 0.U
        state := State.S3
      }
    }

    is (State.S3) {
      when (counter < 6.U) {
        counter := counter + 1.U
        state := State.S3
      } .otherwise {
        counter := 0.U
        state := State.S4
      }
    }

    is (State.S4) {
      when (counter < 2.U) {
        counter := counter + 1.U
        state := State.S4
      } .otherwise {
        counter := 0.U
        state := State.S1
      }
    }
  }

  io.mainGreen := false.B
  io.mainRed := false.B
  io.mainYellow := false.B

  io.branchGreen := false.B
  io.branchRed := false.B
  io.branchYellow := false.B

  switch (state) {
    is (State.S1) {
      io.mainGreen := true.B
      io.branchRed := true.B
    }

    is (State.S2) {
      io.mainYellow := true.B
      io.branchRed := true.B
    }

    is (State.S3) {
      io.mainRed := true.B
      io.branchGreen := true.B
    }

    is (State.S4) {
      io.mainRed := true.B
      io.branchYellow := true.B
    }
  }
}
