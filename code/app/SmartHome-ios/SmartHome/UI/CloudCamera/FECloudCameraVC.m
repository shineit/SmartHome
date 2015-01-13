//
//  FECloudCameraVC.m
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECloudCameraVC.h"
#import "FECollectionViewCameraCell.h"
#import "YSMobilePages.h"
#import "YSPlayerController.h"
#import "YSHTTPClient.h"
#import <SDWebImage/UIImageView+WebCache.h>
#import "YSCamera.h"

@interface FECloudCameraVC ()<YSPlayerControllerDelegate,UICollectionViewDataSource,UICollectionViewDelegate,UICollectionViewDelegateFlowLayout>

@property (nonatomic, strong) UICollectionView *collectionView;
@property (nonatomic, strong) NSMutableArray *cameras;
@property (nonatomic, strong) YSMobilePages *page;
@property (nonatomic, strong) YSPlayerController *ysPlayer;
@property (nonatomic, strong) NSString *token;

@end

@implementation FECloudCameraVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = FEString(@"CLOUD_CAMERA");
        
        if (SYSTEM_VERSION_UP7) {
            UITabBarItem *tabitem = [[UITabBarItem alloc] initWithTitle:FEString(@"CLOUD_CAMERA") image:[UIImage imageNamed:@"tabbar_camera"] selectedImage:nil];
            self.tabBarItem = tabitem;
        }else{
            [self.tabBarItem setFinishedSelectedImage:[UIImage imageNamed:@"tabbar_camera_select"] withFinishedUnselectedImage:[UIImage imageNamed:@"tabbar_camera"]];
        }
        
//        self.cameras = [[NSMutableArray alloc] initWithObjects:@"camera1",@"camera2",@"camera3",@"camera4", nil];
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self initUI];
    [self login];
    _ysPlayer = [[YSPlayerController alloc] initWithDelegate:self];
}


- (void)login {
    NSString *apiKey = YSAppKey; // 已申请的 apikey.
    __weak typeof (self) weakself = self;
    _page = [[YSMobilePages alloc] init];
    [_page login:self.navigationController withAppKey:apiKey complition:^(NSString *accessToken) {
        if (accessToken) {
            NSLog(@"Client access token is: %@", accessToken);
            [[YSHTTPClient sharedInstance] setClientAccessToken:accessToken];
            weakself.token = accessToken;
            [weakself.navigationController popViewControllerAnimated:YES];
            [weakself requestCameras];
        } }];
}

-(void)requestCameras{
    __weak typeof (self) weakself = self;
    [[YSHTTPClient sharedInstance] requestSearchCameraListPageFrom:0 pageSize:30 complition:^(id responseObject, NSError *error) {
        if (responseObject) {
            NSDictionary *dictionary = (NSDictionary *)responseObject;
            NSNumber *resultCode = [dictionary objectForKey:@"resultCode"];
            if (resultCode.intValue == 200) {
                NSMutableArray *carray = [NSMutableArray new];
                for (NSDictionary *item in responseObject[@"cameraList"]) {
                    [carray addObject:[[YSCamera alloc] initWithDictionary:item]];
                }
                weakself.cameras = carray;
                [weakself.collectionView reloadData];
            }
            
        }
    }];
}

-(void)initUI{
    
    UICollectionViewFlowLayout *layout= [[UICollectionViewFlowLayout alloc]init];
    UICollectionView *cview = [[UICollectionView alloc] initWithFrame:self.view.bounds collectionViewLayout:layout];
    cview.alwaysBounceVertical = YES;
    cview.delegate = self;
    cview.dataSource = self;
    cview.autoresizingMask = UIViewAutoresizingFlexibleHeight;
    [cview registerClass:[FECollectionViewCameraCell class] forCellWithReuseIdentifier:@"cell"];
//    cview.backgroundColor = [UIColor lightGrayColor];
    cview.backgroundColor = [UIColor clearColor];
    self.collectionView = cview;
    [self.view addSubview:cview];
    
}

#pragma mark - UICollectionViewDataSource

// The cell that is returned must be retrieved from a call to -dequeueReusableCellWithReuseIdentifier:forIndexPath:
- (UICollectionViewCell *)collectionView:(UICollectionView *)collectionView cellForItemAtIndexPath:(NSIndexPath *)indexPath{
    static NSString * identifier = @"cell";
    FECollectionViewCameraCell * cell = [collectionView dequeueReusableCellWithReuseIdentifier:identifier forIndexPath:indexPath];
    YSCamera *item = self.cameras[indexPath.row];
    cell.backgroundColor = [UIColor whiteColor];
    cell.textLabel.text = item.cameraName;
    [cell.imageView sd_setImageWithURL:[NSURL URLWithString:item.picUrl]];
    return cell;
}

-(NSInteger)numberOfSectionsInCollectionView:(UICollectionView *)collectionView
{
    return 1;
}

- (NSInteger)collectionView:(UICollectionView *)collectionView numberOfItemsInSection:(NSInteger)section{
    return self.cameras.count;
}

#pragma mark - UICollectionViewDelegateFlowLayout

- (CGSize)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout*)collectionViewLayout sizeForItemAtIndexPath:(NSIndexPath *)indexPath
{
    return CGSizeMake(140, 140);
}

-(UIEdgeInsets)collectionView:(UICollectionView *)collectionView layout:(UICollectionViewLayout *)collectionViewLayout insetForSectionAtIndex:(NSInteger)section
{
    return UIEdgeInsetsMake(10, 10, 10, 10);
}

-(void)collectionView:(UICollectionView *)collectionView didSelectItemAtIndexPath:(NSIndexPath *)indexPath{
//    RealPlayViewController *realPlayController = [[RealPlayViewController alloc] init];
//    realPlayController.cameraInfo = cell.cameraInfo;
//    [self.navigationController pushViewController:realPlayController animated:YES];
//    [realPlayController release];
    YSCamera *camera = self.cameras[indexPath.row];
    [self.ysPlayer startRealPlayWithCamera:camera.cameraId accessToken:self.token inView:self.view];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
- (void)playerOperationMessage:(YSPlayerMessageType)msgType withValue:(id)value{
    
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
