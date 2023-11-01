import chisel3._
import chisel3.util.{is, _}

class RegisterFile extends Module {
  val io = IO(new Bundle {
    //Define the module interface here (inputs/outputs)
    val aSel = Input(UInt(4.W))
    val bSel = Input(UInt(4.W))
    val writeSel = Input(UInt(4.W))
    val writeData = Input(UInt(32.W))
    val writeEnable = Input(Bool())

    val a = Output(UInt(32.W))
    val b = Output(UInt(32.W))
  })

  //Implement this module here

  io.a := 0.U
  io.b := 0.U

  val r0 = RegInit (0.U(32.W))
  val r1 = RegInit (0.U(32.W))
  val r2 = RegInit (0.U(32.W))
  val r3 = RegInit (0.U(32.W))
  val r4 = RegInit (0.U(32.W))
  val r5 = RegInit (0.U(32.W))
  val r6 = RegInit (0.U(32.W))
  val r7 = RegInit (0.U(32.W))
  val r8 = RegInit (0.U(32.W))
  val r9 = RegInit (0.U(32.W))
  val r10 = RegInit (0.U(32.W))
  val r11 = RegInit (0.U(32.W))
  val r12 = RegInit (0.U(32.W))
  val r13 = RegInit (0.U(32.W))
  val r14 = RegInit (0.U(32.W))
  val r15 = RegInit (0.U(32.W))

  switch(io.aSel) {
    is("b0000".U) {io.a := r0}
    is("b0001".U) {io.a := r1}
    is("b0010".U) {io.a := r2}
    is("b0011".U) {io.a := r3}
    is("b0100".U) {io.a := r4}
    is("b0101".U) {io.a := r5}
    is("b0110".U) {io.a := r6}
    is("b0111".U) {io.a := r7}
    is("b1000".U) {io.a := r8}
    is("b1001".U) {io.a := r9}
    is("b1010".U) {io.a := r10}
    is("b1011".U) {io.a := r11}
    is("b1100".U) {io.a := r12}
    is("b1101".U) {io.a := r13}
    is("b1110".U) {io.a := r14}
    is("b1111".U) {io.a := r15}
  }

  switch(io.bSel) {
    is("b0000".U) {io.b := r0}
    is("b0001".U) {io.b := r1}
    is("b0010".U) {io.b := r2}
    is("b0011".U) {io.b := r3}
    is("b0100".U) {io.b := r4}
    is("b0101".U) {io.b := r5}
    is("b0110".U) {io.b := r6}
    is("b0111".U) {io.b := r7}
    is("b1000".U) {io.b := r8}
    is("b1001".U) {io.b := r9}
    is("b1010".U) {io.b := r10}
    is("b1011".U) {io.b := r11}
    is("b1100".U) {io.b := r12}
    is("b1101".U) {io.b := r13}
    is("b1110".U) {io.b := r14}
    is("b1111".U) {io.b := r15}
  }
  when (io.writeEnable) {
    switch(io.writeSel) {
      is("b0000".U) {r0 := io.writeData}
      is("b0001".U) {r1 := io.writeData}
      is("b0010".U) {r2 := io.writeData}
      is("b0011".U) {r3 := io.writeData}
      is("b0100".U) {r4 := io.writeData}
      is("b0101".U) {r5 := io.writeData}
      is("b0110".U) {r6 := io.writeData}
      is("b0111".U) {r7 := io.writeData}
      is("b1000".U) {r8 := io.writeData}
      is("b1001".U) {r9 := io.writeData}
      is("b1010".U) {r10 := io.writeData}
      is("b1011".U) {r11 := io.writeData}
      is("b1100".U) {r12 := io.writeData}
      is("b1101".U) {r13 := io.writeData}
      is("b1110".U) {r14 := io.writeData}
      is("b1111".U) {r15 := io.writeData}
    }
  }

}