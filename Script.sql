---Create Customer Table---------------------------------------------------------------------------------------------------------------------------------------
create table customer(customerId int unique not null, customerName varchar(50) not null, password varchar(16) not null, customerBalance int not null );

---Create Employee Table---------------------------------------------------------------------------------------------------------------------------------------
create table employee(employeeId int unique not null, employeeName varchar(50) not null, password varchar(16) not null);

---transfer balance--------------------------------------------------------------------------------------------------------------------------------------------
create or replace procedure transferamount
(
   sender int,
   receiver int,
   amount dec
)
language  plpgsql
as $$
begin 
		update bankingapp.customer set customerbalance = customerbalance - amount where customerid = sender;
		update bankingapp.customer set customerbalance = customerbalance + amount where customerid = receiver;

commit;
end;$$


call transferamount(10, 12, 100)

select * from customer;
select * from employee; 
