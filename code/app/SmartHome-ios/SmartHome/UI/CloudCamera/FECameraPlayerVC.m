//
//  FECameraPlayerVC.m
//  SmartHome
//
//  Created by Seven on 15-2-9.
//  Copyright (c) 2015年 FUEGO. All rights reserved.
//

#import "FECameraPlayerVC.h"
#import "YSPlayerController.h"

@interface FECameraPlayerVC ()<YSPlayerControllerDelegate>
@property (strong, nonatomic) IBOutlet UIView *playerView;
@property (strong, nonatomic) YSPlayerController *control;

@end

@implementation FECameraPlayerVC

- (void)viewDidLoad {
    [super viewDidLoad];
    // Do any additional setup after loading the view.
//    self.playerView
    self.control = [[YSPlayerController alloc] initWithDelegate:self];
    [self.control startRealPlayWithCamera:self.camera.cameraId accessToken:self.accessToken inView:self.playerView];
}

#pragma mark 
- (void)playerOperationMessage:(YSPlayerMessageType)msgType withValue:(id)value{
    
}

- (void)didReceiveMemoryWarning {
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
