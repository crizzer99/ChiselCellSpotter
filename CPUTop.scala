import chisel3._
import chisel3.util._

class CPUTop extends Module {
  val io = IO(new Bundle {
    val done = Output(Bool ())
    val run = Input(Bool ())
    //This signals are used by the tester for loading and dumping the memory content, do not touch
    val testerDataMemEnable = Input(Bool ())
    val testerDataMemAddress = Input(UInt (16.W))
    val testerDataMemDataRead = Output(UInt (32.W))
    val testerDataMemWriteEnable = Input(Bool ())
    val testerDataMemDataWrite = Input(UInt (32.W))
    //This signals are used by the tester for loading and dumping the memory content, do not touch
    val testerProgMemEnable = Input(Bool ())
    val testerProgMemAddress = Input(UInt (16.W))
    val testerProgMemDataRead = Output(UInt (32.W))
    val testerProgMemWriteEnable = Input(Bool ())
    val testerProgMemDataWrite = Input(UInt (32.W))
  })

  //Creating components
  val programCounter = Module(new ProgramCounter())
  val dataMemory = Module(new DataMemory())
  val programMemory = Module(new ProgramMemory())
  val registerFile = Module(new RegisterFile())
  val controlUnit = Module(new ControlUnit())
  val alu = Module(new ALU())

  //Connecting the modules
  programMemory.io.address := programCounter.io.programCounter
  io.done := false.B

  ////////////////////////////////////////////
  //Continue here with your connections
  ////////////////////////////////////////////

  val remainingBits = Wire(UInt(20.W))
  remainingBits := programMemory.io.instructionRead(19, 0)
  val extended = Wire(UInt(32.W))
  extended := Cat(0.U(12.W), remainingBits)

  // ProgramCounter
  programCounter.io.run := io.run
  programCounter.io.jump := (controlUnit.io.condJump & alu.io.zero) | controlUnit.io.imJump
  programCounter.io.programCounterJump := remainingBits(15,0)
  programCounter.io.stop := controlUnit.io.done

  // Control unit
  controlUnit.io.opCode := programMemory.io.instructionRead(31,28)

  // Register File
  registerFile.io.writeSel := programMemory.io.instructionRead(27, 24)
  registerFile.io.aSel := programMemory.io.instructionRead(23, 20)
  registerFile.io.bSel := remainingBits(19,16)
  registerFile.io.writeEnable := controlUnit.io.writeEnable
  registerFile.io.writeData := Mux(controlUnit.io.memToReg, dataMemory.io.dataRead, alu.io.result)

  // ALU
  alu.io.regA := registerFile.io.a
  alu.io.opB := Mux(controlUnit.io.aluSrc, extended, registerFile.io.b)
  alu.io.sel := controlUnit.io.aluOp

  // Data Memory
  dataMemory.io.address := alu.io.result
  dataMemory.io.dataWrite := registerFile.io.b
  dataMemory.io.writeEnable := controlUnit.io.memWrite




  //This signals are used by the tester for loading the program to the program memory, do not touch
  programMemory.io.testerAddress := io.testerProgMemAddress
  io.testerProgMemDataRead := programMemory.io.testerDataRead
  programMemory.io.testerDataWrite := io.testerProgMemDataWrite
  programMemory.io.testerEnable := io.testerProgMemEnable
  programMemory.io.testerWriteEnable := io.testerProgMemWriteEnable
  //This signals are used by the tester for loading and dumping the data memory content, do not touch
  dataMemory.io.testerAddress := io.testerDataMemAddress
  io.testerDataMemDataRead := dataMemory.io.testerDataRead
  dataMemory.io.testerDataWrite := io.testerDataMemDataWrite
  dataMemory.io.testerEnable := io.testerDataMemEnable
  dataMemory.io.testerWriteEnable := io.testerDataMemWriteEnable
}