package edu.sjsu.cmpe275.aop.aspect;

import org.springframework.beans.factory.annotation.Autowired;  // if needed

import edu.sjsu.cmpe275.aop.ProfileService;

import org.aspectj.lang.annotation.Aspect;  // if needed
import org.aspectj.lang.JoinPoint;  // if needed
import org.aspectj.lang.annotation.After;  // if needed
import org.aspectj.lang.annotation.Before;  // if needed


@Aspect
public class AuthorizationAspect {
    /***
     * Following is a dummy implementation of this aspect.
     * You are expected to provide an actual implementation based on the requirements, including adding/removing advises as needed.
     */

	@Autowired ProfileService profileService;
	
	@After("execution(public void edu.sjsu.cmpe275.aop.ProfileService.shareProfile(..))")
	public void dummyAfterAdvice(JoinPoint joinPoint) {
		System.out.printf("After the executuion of the metohd %s\n", joinPoint.getSignature().getName());
		//profileService.shareProfile();
	}
	
	@Before("execution(public void edu.sjsu.cmpe275.aop.ProfileService.unshareProfile(..))")
	public void dummyBeforeAdvice(JoinPoint joinPoint) {
		System.out.printf("Before the executuion of the metohd %s\n", joinPoint.getSignature().getName());
	}
	
}
