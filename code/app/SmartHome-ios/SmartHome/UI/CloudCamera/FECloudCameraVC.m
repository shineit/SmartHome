//
//  FECloudCameraVC.m
//  SmartHome
//
//  Created by Seven on 14-10-19.
//  Copyright (c) 2014年 FUEGO. All rights reserved.
//

#import "FECloudCameraVC.h"
#import "FECollectionViewCameraCell.h"

@interface FECloudCameraVC ()<UICollectionViewDataSource,UICollectionViewDelegate,UICollectionViewDelegateFlowLayout>

@property (nonatomic, strong) UICollectionView *collectionView;
@property (nonatomic, strong) NSMutableArray *cameras;

@end

@implementation FECloudCameraVC

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
        self.title = FEString(@"CAMERA");
        self.cameras = [[NSMutableArray alloc] initWithObjects:@"camera1",@"camera2",@"camera3",@"camera4", nil];
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    [self initUI];
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
    
    cell.backgroundColor = [UIColor whiteColor];
    cell.textLabel.text = self.cameras[indexPath.row];
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

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
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
