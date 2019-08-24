package com.gss.ch02;

import com.gss.ch02.job.Job;
import com.gss.ch02.job.Stream;

public class VehicleCount {

  public static void main(String[] args) {
    Job job = new Job("ch02");

    Stream bridgeStream = job.addSource("bridge", new Bridge());
    bridgeStream.applyOperator("booth", new Booth());

    job.run();
  }
}