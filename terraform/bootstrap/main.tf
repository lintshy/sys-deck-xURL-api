provider "aws" {
  region = "us-west-2"
}

resource "aws_s3_bucket" "tf_state" {
  bucket = "your-terraform-state-bucket"
  versioning {
    enabled = true
  }
}

resource "aws_dynamodb_table" "tf_lock" {
  name         = "terraform-lock-table"
  billing_mode = "PAY_PER_REQUEST"
  hash_key     = "LockID"

  attribute {
    name = "LockID"
    type = "S"
  }
}