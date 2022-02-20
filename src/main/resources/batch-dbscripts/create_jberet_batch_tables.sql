-- auto-generated definition
create table job_instance
(
    jobinstanceid   bigserial
        constraint job_instance_pkey
            primary key,
    version         integer,
    jobname         varchar(512),
    applicationname varchar(512)
)
    using ???;

-- auto-generated definition
create table job_execution
(
    jobexecutionid  bigserial
        constraint job_execution_pkey
            primary key,
    jobinstanceid   bigint not null
        constraint fk_job_execution_job_instance
            references job_instance
            on delete cascade,
    version         integer,
    createtime      timestamp with time zone,
    starttime       timestamp with time zone,
    endtime         timestamp with time zone,
    lastupdatedtime timestamp with time zone,
    batchstatus     varchar(30),
    exitstatus      varchar(512),
    jobparameters   varchar(3000),
    restartposition varchar(255)
)
    using ???;

-- auto-generated definition
create table step_execution
(
    stepexecutionid      bigserial
        constraint step_execution_pkey
            primary key,
    jobexecutionid       bigint not null
        constraint fk_step_exe_job_exe
            references job_execution
            on delete cascade,
    version              integer,
    stepname             varchar(255),
    starttime            timestamp with time zone,
    endtime              timestamp with time zone,
    batchstatus          varchar(30),
    exitstatus           varchar(512),
    executionexception   varchar(2048),
    persistentuserdata   bytea,
    readcount            integer,
    writecount           integer,
    commitcount          integer,
    rollbackcount        integer,
    readskipcount        integer,
    processskipcount     integer,
    filtercount          integer,
    writeskipcount       integer,
    readercheckpointinfo bytea,
    writercheckpointinfo bytea
)
    using ???;

-- auto-generated definition
create table partition_execution
(
    partitionexecutionid integer not null,
    stepexecutionid      bigint  not null
        constraint fk_partition_exe_step_exe
            references step_execution
            on delete cascade,
    version              integer,
    batchstatus          varchar(30),
    exitstatus           varchar(512),
    executionexception   varchar(2048),
    persistentuserdata   bytea,
    readercheckpointinfo bytea,
    writercheckpointinfo bytea,
    constraint partition_execution_pkey
        primary key (partitionexecutionid, stepexecutionid)
)
    using ???;

/** Sequences **/
-- auto-generated definition
create sequence job_execution_jobexecutionid_seq;

alter sequence job_execution_jobexecutionid_seq owner to btadmin;

alter sequence job_execution_jobexecutionid_seq owned by job_execution.jobexecutionid;

-- auto-generated definition
create sequence job_instance_jobinstanceid_seq;

alter sequence job_instance_jobinstanceid_seq owner to btadmin;

alter sequence job_instance_jobinstanceid_seq owned by job_instance.jobinstanceid;

-- auto-generated definition
create sequence step_execution_stepexecutionid_seq;

alter sequence step_execution_stepexecutionid_seq owner to btadmin;

alter sequence step_execution_stepexecutionid_seq owned by step_execution.stepexecutionid;







