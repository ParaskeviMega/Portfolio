module Heilstone();


reg reset, clk;


MCPU cpuinst (clk, reset);


initial begin
  reset=1;
  #10  reset=0;
end

always begin
  #5 clk=0; 
  #5 clk=1; 
end


/********OUR ASSEMBLER*****/

integer file, i;
reg[cpuinst.WORD_SIZE-1:0] memi;
parameter  [cpuinst.OPERAND_SIZE-1:0]  R0  = 0; //4'b0000
parameter  [cpuinst.OPERAND_SIZE-1:0]  R1  = 1; //4'b0001
parameter  [cpuinst.OPERAND_SIZE-1:0]  R2  = 2; //4'b0010
parameter  [cpuinst.OPERAND_SIZE-1:0]  R3  = 3; //4'b0011
parameter  [cpuinst.OPERAND_SIZE-1:0]  R4  = 4; //4'b0100

initial
begin

    for(i=0;i<256;i=i+1)
    begin
      cpuinst.raminst.mem[i]=0;
    end
    cpuinst.regfileinst.R[0]=0;
    cpuinst.regfileinst.R[1]=0;
    cpuinst.regfileinst.R[2]=0;
    cpuinst.regfileinst.R[3]=0; 
    cpuinst.regfileinst.R[4]=0;    
    



                                                                               //memory address: instruction
    i=0;  cpuinst.raminst.mem[0]={cpuinst.OP_SHORT_TO_REG, R0, 8'b00000001};   //1:R0=1;
    i=i+1;cpuinst.raminst.mem[i]={cpuinst.OP_SHORT_TO_REG, R1, 8'b00000011};   //2:R1=1;
    i=i+1;cpuinst.raminst.mem[i]={cpuinst.OP_SUB, R2, R1, R0};                 //3:R2=R1-R0;
                                                                               //
    i=i+1;cpuinst.raminst.mem[i]={cpuinst.OP_BNZ, R2, 8'b00000101};            //4:while(n!=1){
    i=i+1;cpuinst.raminst.mem[i]={cpuinst.OP_SHORT_TO_REG, R3, 8'b00000001};   //5:R3=1;
    i=i+1;cpuinst.raminst.mem[i]={cpuinst.OP_AND, R4, R2, R3};                 //6:R4=R2 AND R3;
    i=i+1;cpuinst.raminst.mem[i]={cpuinst.OP_BNZ, R2, 8'b00001001};            //7:if (R2!=0);
                                                                               //8:n=3n+1
                                                                               //9:n=n/2
    i=i+1;cpuinst.raminst.mem[i]={cpuinst.OP_BNZ, R2, 8'b00000101};            //10:while(n!=1)


    
    
    file = $fopen("program.list","w");
    for(i=0;i<cpuinst.raminst.RAM_SIZE;i=i+1)
    begin
      memi=cpuinst.raminst.mem[i];
      
      $fwrite(file, "%b_%b_%b_%b\n", 
        memi[cpuinst.INSTRUCTION_SIZE-1:cpuinst.INSTRUCTION_SIZE-cpuinst.OPCODE_SIZE],
        memi[cpuinst.OPCODE_SIZE*3-1:2*cpuinst.OPCODE_SIZE],
        memi[cpuinst.OPCODE_SIZE*2-1:cpuinst.OPCODE_SIZE],
        memi[cpuinst.OPCODE_SIZE-1:0]);
    end
    $fclose(file);
end

endmodule

